import XCTest

@testable import SzakdolgozatCryptoApp
final class MessagerInteractor_Test: XCTestCase {
    var interactor: MessagerInteractor?
    let userService: UserService = TestUserService()
    let communityService: CommunityService = TestCommunityService()

    override func setUpWithError() throws {
        interactor = MessagerInteractor(userService, communityService)
    }

    override func tearDownWithError() throws {
        interactor = nil
    }

    func test_MessagerInteractor_sendMessage_messageShouldChangedInService() {
        interactor?.sendMessage(id: "3", message: MessageModel(id: Int.random(in: 1...1000), sender: "tester", senderemail: "tester@test.com", message: "test", time: "2022-10-28 15:15:15", image: false))
        XCTAssertEqual(1, communityService.communities[1].messages.count)
        communityService.communities[1].messages = []
        communityService.communities[1].members = []
    }
}
