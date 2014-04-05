(defproject chroulette "0.1.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [compojure "1.1.6"]
                 [http-kit "2.1.16"]
                 [hiccup "1.0.5"]]
  :main chroulette.server
  :profiles {:dev {:dependencies [[speclj "3.0.2"]
                                  [javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}}
  :plugins [[speclj "3.0.2"]
            [lein-ring "0.8.10"]
            [lein-cljsbuild "1.0.3"]]
  :test-paths ["spec"]
  :cljsbuild
    {:builds
     [{:source-paths ["src/cljs"],
       :id "main",
       :compiler
       {:pretty-print true,
        :output-to "resources/public/js/app.js",
        :optimizations :simple}}]})
