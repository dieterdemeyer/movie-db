package be.dieterdemeyer.moviedb.test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.thoughtworks.selenium.Selenium;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:scenarioTestContext.xml"})
public abstract class ScenarioTestCase {

    @Inject
    private Selenium client;

    @Inject
    private SimpleJdbcTemplate jdbcTemplate;

    @Inject
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    protected EntityManager entityManager;

    private static final FileFilter SQL_FILE_FILTER = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            return pathname.getAbsolutePath().contains(".sql");
        }
    };

    @Before
    public void cleanDatabaseAndSetUp() {
        cleanDatabase();
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                setUp();
            }
        });
    }

    private void cleanDatabase() {
        TreeSet<File> files = new TreeSet<File>(Arrays.asList(new File("../db/scripts/").listFiles(SQL_FILE_FILTER)));
        for (File file : files) {
            SimpleJdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(file), false);
        }
    }

    protected void setUp() {
    }

    @After
    public void cleanUpSession() {
        client.deleteCookie("JSESSIONID", "path=/,domain=localhost,recurse=true");
    }

    protected <E> void persist(E... entities) {
        for (E entity : entities) {
            entityManager.persist(entity);
        }
    }


    protected void open(String url) {
        client.open(url);
    }

    protected void addSelection(String locator, String optionLocator) {
        client.addSelection(locator, optionLocator);
    }

    protected void answerOnNextPrompt(String answer) {
        client.answerOnNextPrompt(answer);
    }

    protected void check(String identifier) {
        client.check(identifier);
    }

    protected void chooseCancelOnNextConfirmation() {
        client.chooseCancelOnNextConfirmation();
    }

    protected void click(String locator) {
        client.click(locator);
    }

    protected void clickAndWait(String locator) {
        click(locator);
        waitForPageToLoad();
    }

    protected void clickLinkAndWait(String linkPattern) {
        clickAndWait("link=" + linkPattern);
    }

    protected void close() {
        client.close();
    }

    protected void fireEvent(String locator, String eventName) {
        client.fireEvent(locator, eventName);
    }

    protected String getAlert() {
        return client.getAlert().trim();
    }

    protected String[] getAllButtons() {
        return client.getAllButtons();
    }

    protected String[] getAllFields() {
        return client.getAllFields();
    }

    protected String[] getAllLinks() {
        return client.getAllLinks();
    }

    protected String getAttribute(String attributeLocator) {
        return client.getAttribute(attributeLocator);
    }

    protected String getBodyText() {
        return client.getBodyText();
    }

    protected String getConfirmation() {
        return client.getConfirmation().trim();
    }

    protected Number getCursorPosition(String locator) {
        return client.getCursorPosition(locator);
    }

    protected String getEval(String script) {
        return client.getEval(script);
    }

    protected String getExpression(String expression) {
        return client.getExpression(expression);
    }

    protected String getHtmlSource() {
        return client.getHtmlSource();
    }

    protected String getLocation() {
        return client.getLocation();
    }

    protected String getPrompt() {
        return client.getPrompt().trim();
    }

    protected String getSelectedId(String selectLocator) {
        return client.getSelectedId(selectLocator);
    }

    protected String[] getSelectedIds(String selectLocator) {
        return client.getSelectedIds(selectLocator);
    }

    protected String getSelectedIndex(String selectLocator) {
        return client.getSelectedIndex(selectLocator);
    }

    protected String[] getSelectedIndexes(String selectLocator) {
        return client.getSelectedIndexes(selectLocator);
    }

    protected String getSelectedLabel(String selectLocator) {
        return client.getSelectedLabel(selectLocator);
    }

    protected String[] getSelectedLabels(String selectLocator) {
        return client.getSelectedLabels(selectLocator);
    }

    protected String getSelectedValue(String selectLocator) {
        return client.getSelectedValue(selectLocator);
    }

    protected String[] getSelectedValues(String selectLocator) {
        return client.getSelectedValues(selectLocator);
    }

    protected String[] getSelectOptions(String selectLocator) {
        return client.getSelectOptions(selectLocator);
    }

    protected String getTable(String tableCellAddress) {
        return client.getTable(tableCellAddress).trim();
    }

    protected String getText(String locator) {
        return client.getText(locator).trim();
    }

    protected String getTitle() {
        return client.getTitle().trim();
    }

    protected String getValue(String locator) {
        return client.getValue(locator).trim();
    }

    protected void goBack() {
        client.goBack();
    }

    protected boolean isAlertPresent() {
        return client.isAlertPresent();
    }

    protected boolean isChecked(String locator) {
        return client.isChecked(locator);
    }

    protected boolean isConfirmationPresent() {
        return client.isConfirmationPresent();
    }

    protected boolean isEditable(String locator) {
        return client.isEditable(locator);
    }

    protected boolean isElementPresent(String locator) {
        return client.isElementPresent(locator);
    }

    protected boolean isPromptPresent() {
        return client.isPromptPresent();
    }

    protected boolean isSomethingSelected(String selectLocator) {
        return client.isSomethingSelected(selectLocator);
    }

    protected boolean isTextPresent(String pattern) {
        return client.isTextPresent(pattern.trim());
    }

    protected boolean isVisible(String locator) {
        return client.isVisible(locator);
    }

    protected void keyDown(String locator, String keycode) {
        client.keyDown(locator, keycode);
    }

    protected void keyPress(String locator, String keycode) {
        client.keyPress(locator, keycode);
    }

    protected void keyUp(String locator, String keycode) {
        client.keyUp(locator, keycode);
    }

    protected void mouseDown(String locator) {
        client.mouseDown(locator);
    }

    protected void mouseOver(String locator) {
        client.mouseOver(locator);
    }

    protected void openPath(String path) {
        open(path);
    }

    protected void refresh() {
        client.refresh();
    }

    protected void removeSelection(String locator, String optionLocator) {
        client.removeSelection(locator, optionLocator);
    }

    protected void select(String selectLocator, String optionLocator) {
        client.select(selectLocator, optionLocator);
    }

    protected void selectWindow(String windowID) {
        client.selectWindow(windowID);
    }

    protected void setCursorPosition(String locator, String position) {
        client.setCursorPosition(locator, position);
    }

    protected void setTimeout(String timeout) {
        client.setTimeout(timeout);
    }

    protected void submit(String formLocator) {
        client.submit(formLocator);
    }

    protected void type(String locator, String value) {
        client.type(locator, value);
    }

    protected void uncheck(String locator) {
        client.uncheck(locator);
    }

    protected void waitForCondition(String script, String timeout) {
        client.waitForCondition(script, timeout);
    }

    protected void waitForPageToLoad() {
        waitForPageToLoad("30");
    }

    protected void waitForPageToLoad(String timeout) {
        client.waitForPageToLoad(Integer.toString(Integer.parseInt(timeout) * 1000));
    }

    protected void waitForPopUp(String windowID, String timeout) {
        client.waitForPopUp(windowID, timeout);
    }
}
