package co.micol.prj.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public String exec(HttpServletRequest request, HttpServletResponse response); // request,response 객체는 서버가 스스로 만듬. 매개변수 없어도 값이 자동으로 담아짐
}
