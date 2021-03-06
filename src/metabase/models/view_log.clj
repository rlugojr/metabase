(ns metabase.models.view-log
  (:require [toucan.models :as models]
            [metabase.models.interface :as i]
            [metabase.util :as u]))


(models/defmodel ViewLog :view_log)

(defn- pre-insert [log-entry]
  (let [defaults {:timestamp (u/new-sql-timestamp)}]
    (merge defaults log-entry)))

(u/strict-extend (class ViewLog)
  models/IModel
  (merge models/IModelDefaults
         {:pre-insert pre-insert})
  i/IObjectPermissions
  (merge i/IObjectPermissionsDefaults
         {:can-read?  (constantly true)
          :can-write? (constantly true)}))
