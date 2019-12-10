var insert = function(intervals, newInterval) {
    if (newInterval.length < 2) {
      console.log(1);
      return intervals;
    }

    var outIntervals = [];
    if (intervals.length == 0) {
      outIntervals[0] = newInterval;
      return outIntervals;
    }
    console.log(2);
    var intervalsLength = intervals.length;
    console.log('intervalsLength:', intervalsLength);
    var currentOutIndex = 0;
    var minPoint = newInterval[0];
    var maxPoint = newInterval[1];
    for(i = 0; i < intervalsLength; i ++) {
      // 找到最终定位地址
      console.log('intervals[i][0]:', i);
      console.log('maxPoint:', maxPoint);
      if (intervals[i][0] > maxPoint) {
        console.log('c:11221');
        // 开始执行添加插入的数组
        outIntervals[currentOutIndex++] = [minPoint, maxPoint];
        console.log('a:', outIntervals);
        for(j = i; j < intervalsLength; j ++) {
            outIntervals[currentOutIndex++] = intervals[j];
            console.log('b:', outIntervals);
        }
        break;
      }

        // 最后一个
      if((i == intervalsLength - 1) && intervals[i][1] < minPoint) {
        console.log('c:111');
        // 把最后一个先放进去，在放入插入的。
        outIntervals[currentOutIndex++] = intervals[i];
        outIntervals[currentOutIndex++] = [minPoint, maxPoint];
        console.log('c:', outIntervals);
        break;
      }

      if (intervals[i][0] <= maxPoint && intervals[i][1] >= minPoint) {
        console.log('e:', intervals[i]);
          if (minPoint > intervals[i][0]) {
              minPoint = intervals[i][0];
          }
          
          if(maxPoint < intervals[i][1]) {
              maxPoint = intervals[i][1];
          }
          console.log('i:', intervals.length - 1)
          if (i === intervals.length - 1) {
              // 如果是最后一个，且有交集，则需要加入
              console.log('d:', outIntervals);
              outIntervals[currentOutIndex++] = [minPoint, maxPoint];
          }
        
        } else {
            // 没有交集，也没到终点，直接放入.
            console.log('xx11:');
            outIntervals[currentOutIndex++] = intervals[i];
            console.log('xx:', outIntervals);
        }
    }
    return outIntervals;

};

console.log(insert([[1,3],[6,9]], [2,5]));