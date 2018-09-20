
#import "RNBuildConfig.h"

@implementation RNBuildConfig

RCT_EXPORT_MODULE(RNBuildConfig)

- (NSDictionary *)constantsToExport
{
    return [NSBundle mainBundle].infoDictionary
}

@end
  
