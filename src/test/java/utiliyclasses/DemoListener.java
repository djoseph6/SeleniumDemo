package utiliyclasses;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
@Listeners
public class DemoListener extends CommonMethods implements ITestListener{

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			CommonMethods.takeScreenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
