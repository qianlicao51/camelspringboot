package com.study.camelconfig.adapter;

import com.study.utils.ConfigUtils;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/11 10:42
 * 最大连接数校验,无用连接回收
 * 50个最大连接
 * 100秒无数据交互强制关闭session
 */

@Component("keepAliveFilter")
public class KeepAliveFilter extends IoFilterAdapter {

    private final static Logger log = LoggerFactory.getLogger(KeepAliveFilter.class);

    @Override
    public void sessionCreated(NextFilter nextFilter, IoSession session) {

        Collection<IoSession> sessions = session.getService().getManagedSessions().values();
        log.info(session.getId() + " Session Opened..." + session.toString() + "SessionList Size:" + sessions.size());
        long nowTime = System.currentTimeMillis();
        for (IoSession s : sessions) {
            if ((nowTime - s.getLastReadTime()) > ConfigUtils.SESSION_TIMEOUT) {
                log.error(s.getId() + " 强制回收无操作连接 " + s.toString());
                s.closeNow();
            }
        }

        if (sessions.size() > ConfigUtils.MAX_CONNECTION) {
            log.error(session.getId() + " 超出最大连接限制,强制关闭连接 " + session.toString());
            session.closeOnFlush();
            return;
        }
        nextFilter.sessionCreated(session);
    }

    @Override
    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession session) {
        log.info(session.getId() + " Session closed..." + session.toString());
        nextFilter.sessionClosed(session);
    }

}
