package org.TodayTix.PageObjects;

import org.TodayTix.Utils;
import org.openqa.selenium.By;

public class Encore_homePage extends Utils{
    Utils utils = new Utils();

    // Locators
    By _gotItButton = By.cssSelector("[class='cc-compliance']");
    By _searchField = By.cssSelector("[type='search']");
    By _quickSearchProductThumbnail = By.cssSelector("[class='o-list o-list--unstyled c-quick-search__list'] [class*='search-results-product__thumbnail']");
    By _quickSearchProductInfo = By.cssSelector("[class='o-list o-list--unstyled c-quick-search__list'] [class='c-quick-search__item-info'] span");
    By _quickSearchItemPriceLabel = By.cssSelector("[class='o-list o-list--unstyled c-quick-search__list'] [class='c-quick-search__item-price-label'] span");
    By _quickSearchProductOption = By.cssSelector("[class='c-quick-search__item t-card-primary-hover']");
    By _findTicketsButton = By.cssSelector("[class*='o-btn__cta--primary c-quick-search__btn t-btn-super']");

    // Methods
    public void selectProductAndFindTickets() {
        driver.get("https://www.encoretickets.co.uk/");
        utils.waitForElementDisplay(_gotItButton);
        driver.findElement(_gotItButton).click();
        utils.waitForElementDisplay(_searchField);
        driver.findElement(_searchField).sendKeys("TINA-The Tina Turner Musical");
        driver.findElement(_quickSearchProductThumbnail).isDisplayed();
        utils.assertTextOfElement("tina - the tina turner musical", _quickSearchProductInfo);
        driver.findElement(_quickSearchItemPriceLabel).isDisplayed();
        driver.findElement(_quickSearchProductOption).click();
        driver.findElement(_findTicketsButton).click();
    }
}
