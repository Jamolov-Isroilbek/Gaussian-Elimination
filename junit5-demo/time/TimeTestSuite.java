package time;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
  TimeStructureTest.class
//  , StructureTest02_WorldTimes.class

  ,TimeTest.class
//  ,WorldTimesTest.class
})
public class TimeTestSuite {}
