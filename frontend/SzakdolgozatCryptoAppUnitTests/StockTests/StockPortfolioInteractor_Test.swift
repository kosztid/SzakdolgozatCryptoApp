import XCTest
import Combine

@testable import SzakdolgozatCryptoApp
final class StockPortfolioInteractor_Test: XCTestCase {

    var cancellables = Set<AnyCancellable>()
    var interactor: StockPortfolioInteractor?

    override func setUpWithError() throws {
        interactor = StockPortfolioInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }

    func test_StockPortfolioInteractor_heldstocks_countShouldbetwo() throws {
        var list: [StockServerModel] = []
        interactor?.getHeld()
            .sink { items in
                list = items
            }
            .store(in: &cancellables)
        XCTAssertEqual(2, list.count)
    }
    func test_StockPortfolioInteractor_heldfavstocks_countShouldbeOne() throws {
        var list: [StockServerModel] = []
        interactor?.getFavs()
            .sink { items in
                list = items
            }
            .store(in: &cancellables)
        XCTAssertEqual(1, list.count)
    }

    func test_StockPortfolioInteractor_setFav_favsShouldContain() throws {
        interactor?.setFav("AAPL")
        var list: [StockServerModel] = []
        interactor?.getFavs()
            .sink { items in
                list = items
            }
            .store(in: &cancellables)
        XCTAssertFalse(list.contains { $0.stockSymbol == "AAPL"})
        interactor?.setFav("AAPL")
    }


    func test_StockPortfolioInteractor_ownedCoins_countShouldbeThree() throws {
        var list: [StockServerModel] = []
        interactor?.getOwned()
            .sink { items in
                list = items
            }
            .store(in: &cancellables)
        XCTAssertEqual(3, list.count)
    }
}
