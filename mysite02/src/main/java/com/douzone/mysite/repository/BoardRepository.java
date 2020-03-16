package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {

	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// Statement 객체 생성
			// SQL문 실행
			String sql = "   select a.no, a.title, b.name, a.hit, a.reg_date, b.no, a.g_no, a.o_no, a.depth"
					+ "     from board a, user b" + "    where a.user_no = b.no" + " order by g_no desc, o_no asc";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 결과 가져오기(사용하기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String username = rs.getString(3);
				int hit = rs.getInt(4);
				String regdate = rs.getString(5);
				Long userno = rs.getLong(6);
				int gno = rs.getInt(7);
				int ono = rs.getInt(8);
				int depth = rs.getInt(9);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUsername(username);
				vo.setHit(hit);
				vo.setRegdate(regdate);
				vo.setUserno(userno);
				vo.setGno(gno);
				vo.setOno(ono);
				vo.setDepth(depth);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public BoardVo titleandcontents(BoardVo vo) {
		BoardVo boardVo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// SQL문 실행
			String sql = "select a.title, a.contents, a.no, a.g_no, a.o_no, a.depth" + " from board a, user b"
					+ " where a.user_no = b.no" + "  and a.no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			rs = pstmt.executeQuery();

			// 결과 가져오기(사용하기)
			if (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long no = rs.getLong(3);
				int gno = rs.getInt(4);
				int ono = rs.getInt(5);
				int depth = rs.getInt(6);

				boardVo = new BoardVo();
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setNo(no);
				boardVo.setGno(gno);
				boardVo.setOno(ono);
				boardVo.setDepth(depth);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardVo;
	}

	public int titleandcontentsupdate(BoardVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set hit = hit+1 where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int modiupdate(BoardVo authcons) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set title = ?, contents = ?, reg_date = now()" + "where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, authcons.getTitle());
			pstmt.setString(2, authcons.getContents());
			pstmt.setLong(3, authcons.getNo());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int insertborad(BoardVo boardVo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values(null, ?, ?, 1, now(), (SELECT IFNULL(MAX(g_no) + 1, 1) FROM board b), 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setLong(3, boardVo.getUserno());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public int listdelete(BoardVo authconsVo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = " update board set title = '' where no = ? and user_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, authconsVo.getNo());
			pstmt.setLong(2, authconsVo.getUserno());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public int commentupdate(BoardVo authconsVo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board" + " set o_no = o_no + 1" + " where g_no = ? and o_no > ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, authconsVo.getGno());
			pstmt.setInt(2, authconsVo.getOno());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int commentinsert(BoardVo authconsVo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values(null, ?, ?, 1, now(), ?, ?+1, ?+1, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, authconsVo.getTitle());
			pstmt.setString(2, authconsVo.getContents());
			pstmt.setInt(3, authconsVo.getGno());
			pstmt.setInt(4, authconsVo.getOno());
			pstmt.setInt(5, authconsVo.getDepth());
			pstmt.setLong(6, authconsVo.getUserno());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public List<BoardVo> FindList(BoardVo vo1) {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// Statement 객체 생성
			// SQL문 실행
			String sql = "   select a.no, a.title, b.name, a.hit, a.reg_date, b.no, a.g_no, a.o_no, a.depth"
					+ " from board a, user b" + " where a.user_no = b.no" + " and a.title like concat ('%', ?, '%')"
					+ " group by a.no";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo1.getTitle());
			rs = pstmt.executeQuery();

			// 결과 가져오기(사용하기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String username = rs.getString(3);
				int hit = rs.getInt(4);
				String regdate = rs.getString(5);
				Long userno = rs.getLong(6);
				int gno = rs.getInt(7);
				int ono = rs.getInt(8);
				int depth = rs.getInt(9);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUsername(username);
				vo.setHit(hit);
				vo.setRegdate(regdate);
				vo.setUserno(userno);
				vo.setGno(gno);
				vo.setOno(ono);
				vo.setDepth(depth);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://192.168.1.106:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}
}
