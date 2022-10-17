import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('Submitted Transaction', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('Submitted Transactions Check', () => {
    page.navigateToSubTxn();
    expect(page.getText('#createNewBtn')).toEqual('Create New');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
