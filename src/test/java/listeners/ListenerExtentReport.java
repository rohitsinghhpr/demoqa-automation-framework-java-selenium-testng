package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListenerExtentReport implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal <ExtentTest> test = new ThreadLocal < > ();
    private static Map < String, ExtentTest> classLevelTests = new ConcurrentHashMap < > ();
    public static ExtentTest getExtentTest() {return test.get();}

    private void configureReport() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportFileName = "Test_Report_" + timeStamp + ".html";
        String reportsDirPath = System.getProperty("user.dir") + "/reports";
        File reportsDir = new File(reportsDirPath);
        if (!reportsDir.exists()) reportsDir.mkdirs();
        ExtentSparkReporter spark = new ExtentSparkReporter(reportsDirPath + "/" + reportFileName);
        spark.config().setDocumentTitle("REPORT_TITLE");
        spark.config().setReportName("REPORT_TITLE");
        spark.config().setTheme("DARK".equalsIgnoreCase("STANDARD") ? Theme.DARK : Theme.STANDARD);
        // Add custom CSS
        spark.config().setCss(".test-contents .mt-4{margin-top:3rem!important;}");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("OS Version", System.getProperty("os.version"));
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        if (extent == null) configureReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) {
            System.err.println("Extent is null in onTestStart. Skipping report entry.");
            return;
        }
        //------------------------------------------------------------------------------
        String xmlName = result.getTestContext().getName();
        String className = result.getTestClass().getRealClass().getSimpleName();
        String method = result.getMethod().getMethodName();
        String key = xmlName + "::" + className;
        ExtentTest extentTest;
        if (true) {
            String parentName = "<b>"+className+"</b>"+"::"+xmlName;
            ExtentTest parent = classLevelTests.computeIfAbsent(key, k -> extent.createTest(parentName));
            extentTest = parent.createNode(method);
        } else {
            extentTest = extent.createTest(xmlName);
        }
        test.set(extentTest);
        extentTest.log(Status.INFO, "Test Started: " + method);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;
        String stepName = "Test Passed: " + result.getMethod().getMethodName();
        extentTest.log(Status.PASS, stepName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;
        extentTest.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        extentTest.log(Status.FAIL, result.getThrowable());
    }


}