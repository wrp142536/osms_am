package com.amazon.test;

import javax.jws.WebService;

/** 
 * @author wanghw
 * @date 2015-3-19 
 * @description TODO
 * @version
 */
@WebService
public interface Test {
	public void say(String sessionId);
	public void work(String sessionId);



}
