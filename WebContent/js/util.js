(function(global) {
	var util = {
			jsonStringify : function (obj) {
				var t = typeof (obj);
				if (t != "object" || obj === null) {
			
					// simple data type
					if (t == "string") obj = '"'+obj+'"';
					return String(obj);
			
				}
				else {
					// recurse array or object
					var v, json = [], arr = (obj && obj.constructor == Array);
			
					for (var n in obj) {
						v = obj[n]; t = typeof(v);
			
						if (t == "string") v = '"'+v+'"';
						else if (t == "object" && v !== null) v = this.jsonStringify(v);
			
						json.push((arr ? "" : '"' + n + '":') + String(v));
					}
			
					return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
				}
			},
			
			parseJson : function (str) {
				if (str === "") str = '""';
				eval("var p=" + str + ";");
				return p;
			},
			
			post : function(url, data, callback) {
				$.ajax({
					'url' : url,
					'type' : 'post',
					'cache' : false,
					'contentType' : 'application/json; charset=utf-8',
					'data' : this.jsonStringify(data),
					'dataType' : 'json',
					'error' : function(req, err, ex) {
						alert('request failed.');
					},
					'success' : function(result) {
						callback(result);
					}
				});
			},
			
			get : function(url, callback) {
				$.ajax({
					'url' : url,
					'type' : 'get',
					'cache' : false,
					'contentType' : 'application/json; charset=utf-8',
					'dataType' : 'json',
					'error' : function(req, err, ex) {
						alert('request failed.');
					},
					'success' : function(result) {
						callback(result);
					}
				});
			}
	};
	
	global.util = util;
})(this);