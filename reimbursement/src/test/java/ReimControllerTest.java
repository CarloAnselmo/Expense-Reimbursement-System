import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.web.controller.ReimbursementController;
import com.web.model.ReimbursementDTO;
import com.web.service.ReimbursementService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReimControllerTest {

	private ReimbursementController rc;
	private ReimbursementService rs;
	HttpServletRequest req;
	HttpServletResponse res;

	@Before
	public void setup() {
		rc = new ReimbursementController();
		rs = Mockito.mock(ReimbursementService.class);
		req = Mockito.mock(HttpServletRequest.class);
		res = Mockito.mock(HttpServletResponse.class);
	}

	@Test
	public void sendAllTest() {
		Mockito.when(rs.ViewAllReimbursements()).thenReturn(new ArrayList<ReimbursementDTO>());
		List<ReimbursementDTO> test = rs.ViewAllReimbursements();
		assertTrue(test.size()==0);
	}
	
	@Test
	public void sendPendingTest() {
		Mockito.when(rs.viewPendingReimbursements()).thenReturn(new ArrayList<ReimbursementDTO>());
		List<ReimbursementDTO> test = rs.viewPendingReimbursements();
		assertTrue(test.size()==0);
	}
	
	@Test
	public void sendCompleteTest() {
		Mockito.when(rs.viewCompletedReimbursements()).thenReturn(new ArrayList<ReimbursementDTO>());
		List<ReimbursementDTO> test = rs.viewCompletedReimbursements();
		assertTrue(test.size()==0);
	}
	
	@Test
	public void sendSpecificTest() {
		Mockito.when(rs.viewUserReimbursements(null)).thenReturn(new ArrayList<ReimbursementDTO>());
		List<ReimbursementDTO> test = rs.viewUserReimbursements(null);
		assertTrue(test.size()==0);
	}


}
