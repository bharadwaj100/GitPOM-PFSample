package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		System.out.println("==> "+testName +"  started execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 String testName = result.getName();
		 System.out.println("==> "+testName +"  got success");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		 String testName = result.getName();
		 System.out.println("==> "+testName +"  got Failed, Screen shot taken");
		 System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 String testName = result.getName();
		 System.out.println("==> "+testName +"  got skipped");
		 System.out.println(result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution of Project Test Finished");
	}

}
