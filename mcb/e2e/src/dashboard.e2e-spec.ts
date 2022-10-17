import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('Dashboard', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('dashboard page check', () => {
    page.navigateToDasboard();
    expect(page.getText('#new_transaction')).toEqual('New Transaction');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
