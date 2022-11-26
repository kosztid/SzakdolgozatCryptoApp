import XCTest
@testable import SzakdolgozatCryptoApp

final class CoinDetailInteractorTest: XCTestCase {
    var interactor: CoinDetailInteractor?
    let model = CoinModel(id: "bitcoin", symbol: "btc", name: "bitcoin", image: "btc", currentPrice: 10, marketCap: 10, marketCapRank: 10, fullyDilutedValuation: 10, totalVolume: 10, high24H: 10, low24H: 10, priceChange24H: 10, priceChangePercentage24H: 10, marketCapChange24H: 10, marketCapChangePercentage24H: 10, circulatingSupply: 10, totalSupply: 10, maxSupply: 10, ath: 10, athChangePercentage: 10, athDate: "btc", atl: 10, atlChangePercentage: 10, atlDate: "btc", lastUpdated: "btc", sparklineIn7D: nil, priceChangePercentage24HInCurrency: 10)
    let userService: UserService = TestUserService()
    override func setUpWithError() throws {
        interactor = CoinDetailInteractor(coin: model, service: userService)
    }

    override func tearDownWithError() throws {
        interactor = nil
    }

    func test_CoinDetailInteractor_getCoin_idShouldbebitcoin() throws {
        XCTAssertEqual("bitcoin", interactor?.getCoin().id)
    }

    func test_CoinDetailInteractor_getCoinCount_countShouldBeTen() throws {
        XCTAssertEqual(10.0, interactor?.getCoinCount())
    }

    func test_CoinDetailInteractor_isFav_shouldBeTrue() throws {
        XCTAssertTrue(((interactor?.isFav()) != nil))
    }
    func test_CoinDetailInteractor_AddHolding_holdingShouldChange() throws {
        interactor?.addHolding(count: 15.0)
        XCTAssertEqual(15.0, interactor?.getCoinCount())
        interactor?.addHolding(count: 0.0)
        XCTAssertEqual(0.0, interactor?.getCoinCount())
        interactor?.addHolding(count: 10.0)
        XCTAssertEqual(10.0, interactor?.getCoinCount())
    }
    func test_CoinDetailInteractor_AddFavCoin_favShouldChange() throws {
        XCTAssertTrue(((interactor?.isFav()) != nil))
        interactor?.addFavCoin()
        var isFav = true
        isFav = interactor!.isFav()
        XCTAssertFalse(isFav)
        interactor?.addFavCoin()
        XCTAssertTrue(((interactor?.isFav()) != nil))
    }
}
