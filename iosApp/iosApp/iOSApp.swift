import SwiftUI
import data

@main
struct iOSApp: App {
    
    init() {
        let _ = PlatformKt.doInitKoinIos(
            isDebug: true//,
            //userDefaults: UserDefaults(suiteName: "KMMRealEstateShowcase")!
        )
        
        let workerBgTaskManager = CoreDataResolver.shared.getWorkerBgTaskManager()
        workerBgTaskManager.registerTasks()
//        workerBgTaskManager.dispatchOperationFetchPropertyData()
        
//        Task {
//            try await workerBgTaskManager.fetchPropertyData()
//            print("Done workerBgTaskManager.fetchPropertyData()")
//        }
        
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView()
		}
	}
}
