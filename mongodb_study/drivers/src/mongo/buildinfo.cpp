
#include <string>
#include <boost/version.hpp>

#include "mongo/util/version.h"

namespace mongo {
    const char * gitVersion() { return "f5e83eae9cfbec7fb7a071321928f00d1b0c5207"; }
    std::string sysInfo() { return "Linux ip-10-2-29-40 2.6.21.7-2.ec2.v1.2.fc8xen #1 SMP Fri Nov 20 17:48:28 EST 2009 x86_64 BOOST_LIB_VERSION=" BOOST_LIB_VERSION ; }
}  // namespace mongo
