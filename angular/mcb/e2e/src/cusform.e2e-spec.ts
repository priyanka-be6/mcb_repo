import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('Customer Form', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('Multivalue Form Check', () => {
    page.navigateToCusForm();
    page.getElement('#addMore').click();
    let cnt = page.getElementCount('.refClass');
    expect(cnt).toBe(2);
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
