package com.wxqts.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.wxqts.rpc.JsonRpcTokenService;
import com.wxqts.rpc.JsonRpcTokenServiceImpl;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月23日 
 */
public class TokenServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private JsonRpcServer rpcServer = null;

    public TokenServlet() {
        super();
        rpcServer = new JsonRpcServer(new ObjectMapper(), new JsonRpcTokenServiceImpl(), JsonRpcTokenService.class);
    }

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	rpcServer.handle(request, response);
    }
}
