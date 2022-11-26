import XCTest

@testable import SzakdolgozatCryptoApp
final class AccountInteractor_Test: XCTestCase {
    var interactor: AccountInteractor?
    override func setUpWithError() throws {
        interactor = AccountInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }

    func test_AccountInteractor_changeVisibility_visibilityShouldChange() {
        var visible = interactor!.getVisibility()
        XCTAssertFalse(visible)
        interactor?.changeVisibility()
        visible = interactor!.getVisibility()
        XCTAssertTrue(visible)
    }
}
