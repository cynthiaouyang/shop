package com.graduation.project.hui.interceptor;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.graduation.project.hui.domain.Member;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -2276280173300917683L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		Member user = (Member) session.get("loginMember");

		// 如果没有登陆，都返回重新登陆

		if (user != null) {
			return invocation.invoke();
		}
		String namespace = invocation.getProxy().getNamespace();
		if (StringUtils.isNotBlank(namespace) && namespace.equals("/member")) {
			String actionName = invocation.getProxy().getActionName();
			if (StringUtils.isNotBlank(actionName)
					&& actionName.equals("memberAction")) {
				String method = invocation.getProxy().getMethod();
				if (StringUtils.isNotBlank(method)
						&& method.equals("getEntity")) {
					return invocation.invoke();
				}
			}
		}

		ctx.put("tip", "Login please");
		return Action.LOGIN;
	}

}
