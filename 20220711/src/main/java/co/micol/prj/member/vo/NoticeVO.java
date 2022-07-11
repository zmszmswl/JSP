package co.micol.prj.member.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO {
	private int noticeId;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeSubject;
	private Date noticeDate;
	private int noticeHit;
	private String noticeAttech; // 실제파일명
	private String noticeAttechDir; // 파일이 존재하는 물리적 위치명
}
