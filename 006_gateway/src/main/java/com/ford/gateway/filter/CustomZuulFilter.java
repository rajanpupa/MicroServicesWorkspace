package com.ford.gateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class CustomZuulFilter extends ZuulFilter{


	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		System.out.println("path = " + ctx.getRequest().getPathInfo());
		ctx.addZuulRequestHeader("Test", "TestSample");
		return null;
	}

	@Override
	public String filterType() {
		return "customFilter";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

}
