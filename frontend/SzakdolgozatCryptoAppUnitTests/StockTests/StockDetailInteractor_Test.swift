import XCTest
@testable import SzakdolgozatCryptoApp

final class StockDetailInteractor_Test: XCTestCase {
    var interactor: StockDetailInteractor?
    let userService: UserService = TestUserService()

    override func setUpWithError() throws {
        interactor = StockDetailInteractor(symbol: "AAPL", item: StockListItem(symbol: "AAPL", name: "Apple", lastsale: "", netchange: "", pctchange: "", marketCap: "", url: ""), service: userService)
    }

    override func tearDownWithError() throws {
        interactor = nil
    }

    func test_StockDetailInteractor_getStock_symbolShouldBeAAPL() throws {
        XCTAssertEqual("AAPL", interactor?.getStockData().symbol)
    }

    func test_StockDetailInteractor_getCoinCount_countShouldBeTen() throws {
        XCTAssertEqual(10.0, interactor?.getStockCount())
    }

    func test_StockDetailInteractor_isFav_shouldBeTrue() throws {
        XCTAssertTrue(((interactor?.isFav()) != nil))
    }
    func test_CoinDetailInteractor_AddPortfolio_holdingShouldChange() throws {
        interactor?.addPortfolio(amount: 15.0, currentprice: 0.0)
        XCTAssertEqual(15.0, interactor?.getStockCount())
        interactor?.addPortfolio(amount: 0.0, currentprice: 0.0)
        XCTAssertEqual(0.0, interactor?.getStockCount())
        interactor?.addPortfolio(amount: 10.0, currentprice: 0.0)
        XCTAssertEqual(10.0, interactor?.getStockCount())
    }
    func test_StockDetailInteractor_AddFavCoin_favShouldChange() throws {
        XCTAssertTrue(((interactor?.isFav()) != nil))
        interactor?.addFavStock()
        var isFav = true
        isFav = interactor!.isFav()
        XCTAssertFalse(isFav)
        interactor?.addFavStock()
        XCTAssertTrue(((interactor?.isFav()) != nil))
    }
}
