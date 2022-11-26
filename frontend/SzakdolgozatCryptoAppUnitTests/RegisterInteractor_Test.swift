import XCTest
import Combine

@testable import SzakdolgozatCryptoApp
final class RegisterInteractor_Test: XCTestCase {
    var cancellables = Set<AnyCancellable>()
    var interactor: RegisterScreenInteractor?
    var registered = false
    override func setUpWithError() throws {
        interactor = RegisterScreenInteractor()
    }

    override func tearDownWithError() throws {
        interactor = nil
    }
    func test_RegisterScreenInteractor_register_registeredshouldbetrue() throws {
        interactor?.register(email: "registertestuser@test.com", password: "registertestuser")
        let expectation = XCTestExpectation(description: "User should be registered")

        interactor?.getRegistered()
            .sink { data in
                self.registered = data
                print(self.registered)
                expectation.fulfill()
            }
            .store(in: &cancellables)

        wait(for: [expectation], timeout: 5)
        XCTAssertTrue(self.registered)
    }
}
