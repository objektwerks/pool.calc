package pool.dashboard

import pool.Context

class TotalBrominePane(context: Context) extends DashboardTitledPane(context):
  text = context.headerTotalBromine
  range.text = context.dashboardTotalBromineRange
  ideal.text = context.dashboardTotalBromineIdeal
  current.text <== context.model.currentTotalBromine.asString
  average.text <== context.model.averageTotalBromine.asString