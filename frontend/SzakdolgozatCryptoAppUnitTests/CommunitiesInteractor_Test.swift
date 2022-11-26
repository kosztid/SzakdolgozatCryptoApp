import XCTest

@testable import SzakdolgozatCryptoApp
final class CommunitiesInteractor_Test: XCTestCase {
    var interactor: CommunitiesInteractor?
    override func setUpWithError() throws {
        interactor = CommunitiesInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }
    func test_CommunitiesInteractor_addandgetCommunity_shouldcommunitiescountchange() {
        var communities: [CommunityModel] = []
        interactor?.getCommunities()
            .sink { list in
                communities = list
        }
        XCTAssertEqual(communities.count, 2)
        interactor?.addCommunity("addedTest")
        interactor?.getCommunities()
            .sink { list in
                communities = list
        }
        XCTAssertEqual(communities.count, 3)
    }

}
