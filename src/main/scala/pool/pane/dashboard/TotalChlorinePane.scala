package pool.pane.dashboard

import pool.Context

class TotalChlorinePane(context: Context) extends DashboardTitledPane(context):
  text = context.tableTotalChlorine
  currentValue.text <== context.model.currentTotalChlorine.asString
  currentAverage.text <== context.model.averageTotalChlorine.asString