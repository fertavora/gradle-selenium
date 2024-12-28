package tech.fertavora.gradle.tests.saucedemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.ProductDetailsPage;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsTests extends BaseTest {
    private static final String EXPECTED_PRODUCTS_IN_CART = "1";
    private static final String EXPECTED_PRODUCT_ID_IN_LOCAL_STORAGE = "[4]";
    private static final String EXPECTED_PRODUCT_TITLE = "Sauce Labs Backpack";
    private static final String SORTING_NAME_Z_TO_A = "Name (Z to A)";
    private static final String SORTING_NAME_A_TO_Z = "Name (A to Z)";
    private static final String SORTING_PRICE_LOW_TO_HIGH = "Price (low to high)";
    private static final String SORTING_PRICE_HIGH_TO_LOW = "Price (high to low)";
    private static final String SORTING_ERROR_MESSAGE = "Sorting does not match expected criteria!";

    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;

    @BeforeEach
    public void productsPage(){
        productsPage = new ProductsPage(driver);
        productsPage.getTo();
    }

    @Test
    public void addProductToCartFromProductsPage(){
        productsPage.clickButtonAddBackpack();

        String actualAmountProductsInCart = productsPage.getProductsAmountInCart();
        String actualCartAmountInLocalStorage = productsPage.getProductsAmountInCartFromLocalStorage();
        assertText(actualAmountProductsInCart, EXPECTED_PRODUCTS_IN_CART);
        assertText(actualCartAmountInLocalStorage, EXPECTED_PRODUCT_ID_IN_LOCAL_STORAGE);
    }

    @Test
    public void goToProductDetailsByTitleClick(){
        productDetailsPage = productsPage.clickLinkBackpack();
        String actualItemTitle = productDetailsPage.getItemTitle();
        assertText(actualItemTitle, EXPECTED_PRODUCT_TITLE);
    }

    @Test
    public void goToProductDetailsByImageClick(){
        productDetailsPage = productsPage.clickImageBackpack();
        String actualItemTitle = productDetailsPage.getItemTitle();
        assertText(actualItemTitle, EXPECTED_PRODUCT_TITLE);
    }

    @Test
    public void sortProductsAlphabetically(){
        productsPage.selectSorting(SORTING_NAME_Z_TO_A);
        assertTrue(productsPage.areStringsReversedSorted(productsPage.getAllProductLinks()));
        productsPage.selectSorting(SORTING_NAME_A_TO_Z);
        assertTrue(productsPage.areStringsSorted(productsPage.getAllProductLinks()), SORTING_ERROR_MESSAGE);
    }

    @Test
    public void sortProductsByPrice(){
        productsPage.selectSorting(SORTING_PRICE_LOW_TO_HIGH);
        assertTrue(productsPage.areDoublesSorted(productsPage.getAllProductPrices()), SORTING_ERROR_MESSAGE);
        productsPage.selectSorting(SORTING_PRICE_HIGH_TO_LOW);
        assertTrue(productsPage.areDoublesReversedSorted(productsPage.getAllProductPrices()), SORTING_ERROR_MESSAGE);
    }
}
