import XCTest
import Combine

@testable import SzakdolgozatCryptoApp

final class CryptoPortfolioInteractor_Test: XCTestCase {
    var cancellables = Set<AnyCancellable>()
    var interactor: PortfolioInteractor?

    override func setUpWithError() throws {
        interactor = PortfolioInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }
    
    func test_CryptoPortfolioInteractor_heldcoins_countShouldbetwo() throws {
        var cnt = 0
        cnt = interactor?.heldcoins().count ?? 0

        XCTAssertEqual(2, cnt)
    }
    func test_CryptoPortfolioInteractor_heldfavcoins_countShouldbeOne() throws {
        var cnt = 0
        cnt = interactor?.heldfavcoins().count ?? 0

        XCTAssertEqual(1, cnt)
    }

    func test_CryptoPortfolioInteractor_updateFav_favsShouldContain() throws {
        interactor?.updateFav("ethereum")
        XCTAssertTrue(((interactor?.heldfavcoins().contains { $0 == "ethereum"}) != nil))
        interactor?.updateFav("ethereum")
    }

    func test_CryptoPortfolioInteractor_isFav_shouldbeTrueForBtc() throws {
        XCTAssertTrue(((interactor?.isFav("bitcoin")) != nil))
    }
    
    func test_CryptoPortfolioInteractor_ownedCoins_countShouldbeThree() throws {
        var cnt = 0
        cnt = interactor?.ownedcoins().count ?? 0

        XCTAssertEqual(3, cnt)
    }
}
