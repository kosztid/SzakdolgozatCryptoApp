import XCTest
import Combine

@testable import SzakdolgozatCryptoApp
final class LoginInteractor_Test: XCTestCase {
    var cancellables = Set<AnyCancellable>()
    var interactor: LoginScreenInteractor?
    var signedIn = false
    override func setUpWithError() throws {
        interactor = LoginScreenInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }
    func test_LoginScreenInteractor_signin_Signedinshouldbetrue() throws {
        interactor?.signIn(email: "unittestuser@test.com", password: "unittestuser")
        let expectation = XCTestExpectation(description: "User should be logged in")

        interactor?.getSignInStatus()
            .sink { data in
                self.signedIn = data
                print(self.signedIn)
                expectation.fulfill()
            }
            .store(in: &cancellables)

        wait(for: [expectation], timeout: 5)
        XCTAssertTrue(self.signedIn)
    }
}
