import XCTest

final class SzakdolgozatCryptoAppUITests: XCTestCase {

    private var app: XCUIApplication!

    override func setUp() {
        super.setUp()
        self.app = XCUIApplication()
        self.app.launch()
    }

    func testcoinlistdetail() {
        sleep(5)
        let coinsListViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 0)
        sleep(1)
        coinsListViewButton.tap()
        let row = app.collectionViews.cells.containing(.staticText, identifier: "ADA").element
        row.tap()
        sleep(3)

        let backButton = self.app.navigationBars.buttons.element(boundBy: 0)
        backButton.tap()
        sleep(2)
    }

    func testNews() throws {
        sleep(2)
        let newsViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 3)
        sleep(1)
        newsViewButton.tap()

        let firstCell = app.collectionViews.cells.element(boundBy: 0)
        XCTAssertTrue(firstCell.exists)
        firstCell.tap()
        sleep(2)

        let backButton = self.app.navigationBars.buttons.element(boundBy: 0)
        backButton.tap()
        sleep(1)

    }
    // teszteli a bejelentkezést. Bejelentkezik, majd a portfoliora navigál,
    // ahol a tesztfiókhoz megadott coinok létezését vizsgálja majd kijelentkezik
    func testLogin() throws {
        let portfolioViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 1)
        sleep(1)
        portfolioViewButton.tap()

        let portfolioLoginButton = self.app.buttons["PortfolioLoginButton"]
        sleep(1)
        XCTAssert(portfolioLoginButton.exists)
        portfolioLoginButton.tap()

        let loginEmailTextField = self.app.textFields["LoginEmailTextField"]
        sleep(1)
        XCTAssert(loginEmailTextField.exists)
        loginEmailTextField.tap()
        loginEmailTextField.typeText("tesztuser@test.com\n")

        let loginPasswordTextField = self.app.secureTextFields["LoginPasswordTextField"]
        sleep(1)
        XCTAssert(loginPasswordTextField.exists)
        loginPasswordTextField.tap()
        loginPasswordTextField.typeText("tesztuser123\n")

        let loginLoginButton = self.app.buttons["LoginButton"]
        sleep(1)
        XCTAssert(loginLoginButton.exists)
        loginLoginButton.tap()

        sleep(5)
        XCTAssert(app.staticTexts["BTC"].exists)
        XCTAssert(app.staticTexts["ETH"].exists)
        XCTAssert(app.staticTexts["BNB"].exists)

        let favfolioButton = self.app.buttons["FavfolioButton"]
        sleep(2)
        XCTAssert(favfolioButton.exists)
        favfolioButton.tap()

        sleep(3)
        XCTAssert(app.staticTexts["SOL"].exists)
        XCTAssert(app.staticTexts["USDT"].exists)

        let walletButton = self.app.buttons["WalletButton"]
        sleep(1)
        XCTAssert(walletButton.exists)
        walletButton.tap()

        sleep(3)
        XCTAssert(app.staticTexts["USDT"].exists)

        let portfolioAccountButton = self.app.buttons["PortfolioAccountButton"]
        sleep(1)
        XCTAssert(portfolioAccountButton.exists)
        portfolioAccountButton.tap()

        let accountSignOutButton = self.app.buttons["AccountSignOutButton"]
        sleep(1)
        XCTAssert(accountSignOutButton.exists)
        accountSignOutButton.tap()

    }

    // bejelentkezik, majd a portfolioból kitöröl egy coint, ellenőrzi hogy a törlés megtörtént e,
    // majd újra hozzáadja, és annak tényét is ellenőrzni
    func testportfolioadd() throws {
        let portfolioViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 1)
        sleep(1)
        portfolioViewButton.tap()

        let portfolioLoginButton = self.app.buttons["PortfolioLoginButton"]
        sleep(1)
        XCTAssert(portfolioLoginButton.exists)
        portfolioLoginButton.tap()

        let loginEmailTextField = self.app.textFields["LoginEmailTextField"]
        sleep(1)
        XCTAssert(loginEmailTextField.exists)
        loginEmailTextField.tap()
        loginEmailTextField.typeText("tesztuser@test.com\n")

        let loginPasswordTextField = self.app.secureTextFields["LoginPasswordTextField"]
        sleep(1)
        XCTAssert(loginPasswordTextField.exists)
        loginPasswordTextField.tap()
        loginPasswordTextField.typeText("tesztuser123\n")
        sleep(2)

        let loginLoginButton = self.app.buttons["LoginButton"]
        XCTAssert(loginLoginButton.exists)
        loginLoginButton.tap()
        sleep(3)
        let row = app.collectionViews.cells.containing(.staticText, identifier: "BNB").element
        row.swipeLeft()

        self.app.buttons.containing(.staticText, identifier: "Delete").element.tap()

        sleep(1)
        XCTAssert(app.staticTexts["BTC"].exists)
        XCTAssert(app.staticTexts["ETH"].exists)
        XCTAssert(!(app.staticTexts["BNB"].exists))


        let listViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 0)
        listViewButton.tap()

        // tap bnb

        app.collectionViews.cells.containing(.staticText, identifier: "BNB").element.tap()
        self.app.buttons["AddButton"].tap()
        // tap addbutton
        let portfolioadderTextField = self.app.collectionViews.textFields.element(boundBy: 0)
        portfolioadderTextField.tap()
        portfolioadderTextField.typeText("30\n")
        
        sleep(1)
        portfolioViewButton.tap()

        XCTAssert(app.staticTexts["BTC"].exists)
        XCTAssert(app.staticTexts["ETH"].exists)
        XCTAssert(app.staticTexts["BNB"].exists)

        let portfolioAccountButton = self.app.buttons["PortfolioAccountButton"]
        sleep(1)
        XCTAssert(portfolioAccountButton.exists)
        portfolioAccountButton.tap()

        let accountSignOutButton = self.app.buttons["AccountSignOutButton"]
        sleep(1)
        XCTAssert(accountSignOutButton.exists)
        accountSignOutButton.tap()

    }

    func testSubscribe() {
        let portfolioViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 1)
        sleep(1)
        portfolioViewButton.tap()

        let portfolioLoginButton = self.app.buttons["PortfolioLoginButton"]
        sleep(1)
        XCTAssert(portfolioLoginButton.exists)
        portfolioLoginButton.tap()

        let loginEmailTextField = self.app.textFields["LoginEmailTextField"]
        sleep(1)
        XCTAssert(loginEmailTextField.exists)
        loginEmailTextField.tap()
        loginEmailTextField.typeText("tesztuser@test.com\n")

        let loginPasswordTextField = self.app.secureTextFields["LoginPasswordTextField"]
        sleep(1)
        XCTAssert(loginPasswordTextField.exists)
        loginPasswordTextField.tap()
        loginPasswordTextField.typeText("tesztuser123\n")

        let loginLoginButton = self.app.buttons["LoginButton"]
        XCTAssert(loginLoginButton.exists)
        loginLoginButton.tap()
        sleep(2)

        let chatViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 4)
        sleep(1)
        chatViewButton.tap()
        sleep(2)
        self.app.buttons["SubscriptionsButton"].tap()

//        megnézni, hogy nincs lista
        XCTAssertFalse(app.staticTexts["bitcoin"].exists)

        self.app.buttons["AddSubscriptionButton"].tap()

        let row = app.collectionViews.cells.containing(.staticText, identifier: "subscriptiontest@test.com").element
//        feliratkozás
        row.buttons.element(boundBy: 0).tap()
        sleep(5)

        XCTAssertTrue(app.staticTexts["subscriptiontest@test.com"].exists)
        sleep(2)
        self.app.buttons["AddSubscriptionButton"].tap()
        sleep(2)
        //        leiratkozás
        let row2 = app.collectionViews.cells.containing(.staticText, identifier: "subscriptiontest@test.com").element
        row2.buttons.element(boundBy: 0).tap()


        sleep(5)

        //        megnézni, hogy nincs lista
        XCTAssertFalse(app.staticTexts["subscriptiontest@test.com"].exists)
//        kijelentkezés
        portfolioViewButton.tap()

        let portfolioAccountButton = self.app.buttons["PortfolioAccountButton"]
        sleep(1)
        XCTAssert(portfolioAccountButton.exists)
        portfolioAccountButton.tap()

        let accountSignOutButton = self.app.buttons["AccountSignOutButton"]
        sleep(1)
        XCTAssert(accountSignOutButton.exists)
        accountSignOutButton.tap()
    }

    func testchat() throws {
        let portfolioViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 1)
        sleep(1)
        portfolioViewButton.tap()

        let portfolioLoginButton = self.app.buttons["PortfolioLoginButton"]
        sleep(1)
        XCTAssert(portfolioLoginButton.exists)
        portfolioLoginButton.tap()

        let loginEmailTextField = self.app.textFields["LoginEmailTextField"]
        sleep(1)
        XCTAssert(loginEmailTextField.exists)
        loginEmailTextField.tap()
        loginEmailTextField.typeText("tesztuser@test.com\n")

        let loginPasswordTextField = self.app.secureTextFields["LoginPasswordTextField"]
        sleep(1)
        XCTAssert(loginPasswordTextField.exists)
        loginPasswordTextField.tap()
        loginPasswordTextField.typeText("tesztuser123\n")

        let loginLoginButton = self.app.buttons["LoginButton"]
        XCTAssert(loginLoginButton.exists)
        loginLoginButton.tap()

        let chatViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 4)
        sleep(1)
        chatViewButton.tap()
        sleep(1)
        let row = app.collectionViews.cells.containing(.staticText, identifier: "TesztCommunity").element
        row.tap()

        let messageTextfield = self.app.textFields["MessageTextfield"]
        sleep(1)
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        let stringdate = dateFormatter.string(from: Date())
        XCTAssert(messageTextfield.exists)
        messageTextfield.tap()
        sleep(1)
        messageTextfield.typeText("tesztuzenet \(stringdate)\n")

        let messageSendButton = self.app.buttons["MessageSendButton"]
        sleep(1)
        XCTAssert(messageSendButton.exists)
        messageSendButton.tap()

        sleep(2)

        XCTAssert(app.staticTexts["tesztuzenet \(stringdate)"].exists)
        sleep(2)

        let messageMembersButton = self.app.buttons["MessageMembersButton"]
        sleep(1)
        XCTAssert(messageMembersButton.exists)
        messageMembersButton.tap()
        sleep(1)

        XCTAssert(app.staticTexts["tesztuser@test.com"].exists)

        sleep(1)
        let backButton = self.app.navigationBars.buttons.element(boundBy: 0)
        backButton.tap()
        sleep(1)
        // MessageMembersButton
        portfolioViewButton.tap()

        let portfolioAccountButton = self.app.buttons["PortfolioAccountButton"]
        sleep(1)
        XCTAssert(portfolioAccountButton.exists)
        portfolioAccountButton.tap()

        let accountSignOutButton = self.app.buttons["AccountSignOutButton"]
        sleep(1)
        XCTAssert(accountSignOutButton.exists)
        accountSignOutButton.tap()
    }

    // tesztel a walletből egy váltást usdt-ről bnb re majd vissza.
    // swiftlint:disable:next function_body_length
    func testSwap() throws {
        let portfolioViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 1)
        sleep(1)
        portfolioViewButton.tap()

        let portfolioLoginButton = self.app.buttons["PortfolioLoginButton"]
        sleep(1)
        XCTAssert(portfolioLoginButton.exists)
        portfolioLoginButton.tap()

        let loginEmailTextField = self.app.textFields["LoginEmailTextField"]
        sleep(1)
        XCTAssert(loginEmailTextField.exists)
        loginEmailTextField.tap()
        loginEmailTextField.typeText("tesztuser@test.com\n")

        let loginPasswordTextField = self.app.secureTextFields["LoginPasswordTextField"]
        sleep(1)
        XCTAssert(loginPasswordTextField.exists)
        loginPasswordTextField.tap()
        loginPasswordTextField.typeText("tesztuser123\n")

        let loginLoginButton = self.app.buttons["LoginButton"]
        sleep(2)
        XCTAssert(loginLoginButton.exists)
        loginLoginButton.tap()

        let swapFolioViewButton = self.app.tabBars.firstMatch.buttons.element(boundBy: 2)
        sleep(1)
        swapFolioViewButton.tap()

        let swapSellSelectorButton = self.app.buttons["SwapSellSelectorButton"]
        sleep(1)
        XCTAssert(swapSellSelectorButton.exists)
        swapSellSelectorButton.tap()

        let seachBarTextField = self.app.textFields["SeachBarTextField"]
        sleep(1)
        XCTAssert(seachBarTextField.exists)
        seachBarTextField.tap()
        seachBarTextField.typeText("USDT\n")

        let firstCellsell = app.collectionViews.cells.element(boundBy: 0)
        XCTAssertTrue(firstCellsell.exists)
        firstCellsell.tap()
        sleep(1)

        let swapBuySelectorButton = self.app.buttons["SwapBuySelectorButton"]
        sleep(1)
        XCTAssert(swapBuySelectorButton.exists)
        swapBuySelectorButton.tap()
        sleep(1)
        XCTAssert(seachBarTextField.exists)
        seachBarTextField.tap()
        seachBarTextField.typeText("BNB\n")

        let firstCell = app.collectionViews.cells.element(boundBy: 0)
        XCTAssertTrue(firstCell.exists)
        firstCell.tap()
        sleep(1)

        let swapBuyTextField = self.app.textFields["SwapBuyTextField"]
        sleep(1)
        XCTAssert(swapBuyTextField.exists)
        swapBuyTextField.tap()
        swapBuyTextField.typeText("1\n")

        let swapButton = self.app.buttons["SwapButton"]
        sleep(1)
        XCTAssert(swapButton.exists)
        swapButton.tap()

        sleep(1)
        portfolioViewButton.tap()

        let walletButton = self.app.buttons["WalletButton"]
        sleep(1)
        XCTAssert(walletButton.exists)
        walletButton.tap()

        sleep(1)
        XCTAssert(app.staticTexts["BNB"].exists)

        swapFolioViewButton.tap()

        sleep(1)
        XCTAssert(swapSellSelectorButton.exists)
        swapSellSelectorButton.tap()

        sleep(1)
        XCTAssert(seachBarTextField.exists)
        seachBarTextField.tap()
        seachBarTextField.typeText("BNB\n")
        XCTAssertTrue(firstCellsell.exists)
        firstCellsell.tap()
        sleep(1)
        XCTAssert(swapBuySelectorButton.exists)
        swapBuySelectorButton.tap()
        sleep(1)
        XCTAssert(seachBarTextField.exists)
        seachBarTextField.tap()
        seachBarTextField.typeText("USDT\n")

        XCTAssertTrue(firstCell.exists)
        firstCell.tap()
        sleep(1)

        let swapSellTextField = self.app.textFields["SwapSellTextField"]
        sleep(1)
        XCTAssert(swapSellTextField.exists)
        swapSellTextField.tap()
        swapSellTextField.typeText("1\n")

        sleep(1)
        XCTAssert(swapButton.exists)
        swapButton.tap()

        sleep(1)
        portfolioViewButton.tap()

        let portfolioAccountButton = self.app.buttons["PortfolioAccountButton"]
        sleep(1)
        XCTAssert(portfolioAccountButton.exists)
        portfolioAccountButton.tap()

        let accountSignOutButton = self.app.buttons["AccountSignOutButton"]
        sleep(1)
        XCTAssert(accountSignOutButton.exists)
        accountSignOutButton.tap()
    }
}
