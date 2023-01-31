Pod::Spec.new do |spec|
    spec.name                     = 'KMMShowcaseKit'
    spec.version                  = '1.0'
    spec.homepage                 = 'Link to the Shared Module homepage'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'This serves as the Umbrella Module'
    spec.vendored_frameworks      = 'build/cocoapods/framework/KMMShowcaseKit.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '15.4'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':shared:kmmbridge',
        'PRODUCT_MODULE_NAME' => 'KMMShowcaseKit',
    }
                
    spec.script_phases = [
        {
            :name => 'Build KMMShowcaseKit',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end