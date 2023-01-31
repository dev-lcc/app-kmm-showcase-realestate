import SwiftUI
import KMMShowcaseKit

@main
struct iOSApp: App {
    
    init() {
        let _ = InitKoinKt.doInitKoinIos(
            isDebug: true
        )
        
        var workerBgTaskManager = CoreDataResolver.shared.getWorkerBgTaskManager()
//        workerBgTaskManager.registerTasks()
//        workerBgTaskManager.dispatchOperationFetchPropertyData()
        
        Task {
            try await workerBgTaskManager.fetchPropertyData()
            print("Done workerBgTaskManager.fetchPropertyData()")
        }
        
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView(
                viewModel: ViewModelResolver.shared.getListingsViewModel()
            )
		}
//        .backgroundTask(.appRefresh(WorkerBGTaskManager.Companion.shared.TASK_ID_FETCH_PROPERTY_DATA)) {
//            do {
//                try await workerBgTaskManager.fetchPropertyData()
//            } catch {
//                print("iOSApp::backgroundTask() -> error = \(error)")
//            }
//        }
	}
}
