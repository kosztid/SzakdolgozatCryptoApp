import XCTest
import Combine

@testable import SzakdolgozatCryptoApp

final class StockSwapInteractor_Test: XCTestCase {
    var cancellables = Set<AnyCancellable>()
    var interactor: StockSwapInteractor?

    override func setUpWithError() throws {
        interactor = StockSwapInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }

    func test_StockSwapInteractor_ownedamount_amountShouldbe10ForApple() throws {
        XCTAssertEqual(10.0, interactor?.ownedamount(stockSymbol: "AAPL"))
    }

    func test_StockSwapInteractor_isOwned_ShouldbeTrueForBitcoin() throws {
        let stock = StockListItem(symbol: "AAPL", name: "Apple", lastsale: "100", netchange: "", pctchange: "", marketCap: "", url: "")
        XCTAssertTrue((interactor?.isOwned(stock: stock)) != nil)
    }

    func test_CryptoSwapInteractor_swap_walletshouldchange() throws {

        interactor?.swap(stockToSell: "AAPL", sellamount: 1.0, stockToBuy: "GOOGL", buyamount: 1.0)
        XCTAssertEqual(9.0, interactor?.ownedamount(stockSymbol: "AAPL"))
        interactor?.swap(stockToSell: "GOOGL", sellamount: 1.0, stockToBuy: "AAPL", buyamount: 1.0)
        XCTAssertEqual(10.0, interactor?.ownedamount(stockSymbol: "AAPL"))
    }
}
