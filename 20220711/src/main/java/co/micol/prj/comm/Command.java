package co.micol.prj.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command { // FrontCommand모델의 상위객체
	public String exec(HttpServletRequest request, HttpServletResponse response);
}
