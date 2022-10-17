import { browser, by, element } from 'protractor';

export class AppPage {

  navigateTo() {
    return browser.get('/login');
  }
  navigateToDasboard() {
    return browser.get('/dashboard');
  }
  navigateToNewTxn() {
    return browser.get('/new_transaction');
  }
  navigateToSubTxn() {
    return browser.get('/submitted_transactions');
  }
  navigateToCusForm() {
    return browser.get('/customer_form');
  }

  getElement(id) {
    return element(by.css(id));
  }
  getText(idName) {
    return element(by.css(idName)).getText();
  }

  getSubmitBtn(){
    return element(by.css('button[type="submit"]'))
  }

  getElementCount(id){
    return element.all(by.css(id)).count();
  }
}
