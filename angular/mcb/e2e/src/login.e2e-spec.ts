import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('MCB Login', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('login page check', () => {
    page.navigateTo();
    const UserInput = page.getElement('#inputUsername');
    const passwordInput = page.getElement('#inputPassword');      
    const loginBtn = page.getElement('.btn'); 

    UserInput.sendKeys('login@gmail.com');
    passwordInput.sendKeys('password');

    loginBtn.click();
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
