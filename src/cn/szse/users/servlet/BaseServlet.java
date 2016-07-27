package cn.szse.users.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ObjectMapper jsonMapper;
	
	protected BaseServlet() {
		super();
		jsonMapper = new ObjectMapper();
		jsonMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true).configure(
				Feature.ALLOW_SINGLE_QUOTES, true);
	}
    
	protected void dispatcher(String uri, HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher(uri).forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected <T> T parseAsBean(HttpServletRequest req, Class<T> t) throws IOException {
//			InputStream inps = req.getInputStream();
//			ByteArrayOutputStream bops = new ByteArrayOutputStream();
//			byte[] buf = new byte[1024];
//			int len = 0;
//			while((len = inps.read(buf)) > 0) {
//				if(len == buf.length) {
//					bops.write(buf);
//				} else {
//					bops.write(buf, 0, len);
//				}
//			}
			
		return jsonMapper.readValue(req.getInputStream(), t);
	}
	
	protected <T> void wirteAsJson(HttpServletResponse resp, T entity) {
		try {
			resp.setContentType("application/json;charset=utf-8");
			String json = jsonMapper.writeValueAsString(entity);
			resp.getOutputStream().write(json.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
