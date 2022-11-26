import XCTest
import Combine

@testable import SzakdolgozatCryptoApp

final class CryptoSwapInteractor_Test: XCTestCase {
    var cancellables = Set<AnyCancellable>()
    var interactor: SwapInteractor?

    override func setUpWithError() throws {
        interactor = SwapInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }

    func test_CryptoSwapInteractor_ownedamount_amountShouldbe10ForBitcoin() throws {
        let model = CoinModel(id: "bitcoin", symbol: "btc", name: "bitcoin", image: "btc", currentPrice: 10, marketCap: 10, marketCapRank: 10, fullyDilutedValuation: 10, totalVolume: 10, high24H: 10, low24H: 10, priceChange24H: 10, priceChangePercentage24H: 10, marketCapChange24H: 10, marketCapChangePercentage24H: 10, circulatingSupply: 10, totalSupply: 10, maxSupply: 10, ath: 10, athChangePercentage: 10, athDate: "btc", atl: 10, atlChangePercentage: 10, atlDate: "btc", lastUpdated: "btc", sparklineIn7D: nil, priceChangePercentage24HInCurrency: 10)
        XCTAssertEqual(10.0, interactor?.ownedamount(coin: model))
    }

    func test_CryptoSwapInteractor_isOwned_ShouldbeTrueForBitcoin() throws {
        let model = CoinModel(id: "bitcoin", symbol: "btc", name: "bitcoin", image: "btc", currentPrice: 10, marketCap: 10, marketCapRank: 10, fullyDilutedValuation: 10, totalVolume: 10, high24H: 10, low24H: 10, priceChange24H: 10, priceChangePercentage24H: 10, marketCapChange24H: 10, marketCapChangePercentage24H: 10, circulatingSupply: 10, totalSupply: 10, maxSupply: 10, ath: 10, athChangePercentage: 10, athDate: "btc", atl: 10, atlChangePercentage: 10, atlDate: "btc", lastUpdated: "btc", sparklineIn7D: nil, priceChangePercentage24HInCurrency: 10)
        XCTAssertTrue(((interactor?.isOwned(coin: model)) != nil))
    }

    func test_CryptoSwapInteractor_swap_walletshouldchange() throws {
        let model = CoinModel(id: "bitcoin", symbol: "btc", name: "bitcoin", image: "btc", currentPrice: 10, marketCap: 10, marketCapRank: 10, fullyDilutedValuation: 10, totalVolume: 10, high24H: 10, low24H: 10, priceChange24H: 10, priceChangePercentage24H: 10, marketCapChange24H: 10, marketCapChangePercentage24H: 10, circulatingSupply: 10, totalSupply: 10, maxSupply: 10, ath: 10, athChangePercentage: 10, athDate: "btc", atl: 10, atlChangePercentage: 10, atlDate: "btc", lastUpdated: "btc", sparklineIn7D: nil, priceChangePercentage24HInCurrency: 10)
        interactor?.swap("bitcoin", 1.0, "ethereum", 1.0)
        XCTAssertEqual(9.0, interactor?.ownedamount(coin: model))
        interactor?.swap("ethereum", 1.0, "bitcoin", 1.0)
        XCTAssertEqual(10.0, interactor?.ownedamount(coin: model))
    }
}
