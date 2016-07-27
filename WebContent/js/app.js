(function(global) {
	var app = {
			config : {
				urlUserSearch: 'normalSearch',
				urlDepartmentLoad: 'loadDepartments'
			},
			depMap : {},
			
			/* initialization */
			init : function(config) {
				var that = this;
				if(config) {
					that.config = $.extend({}, that.config, config);
				}
				
				//bind events
				var dateOpts = { changeMonth: true, changeYear: true, dateFormat: "yy-mm-dd" };
				$('#bornMin').datepicker(dateOpts);
				$('#bornMax').datepicker(dateOpts);
				
				$('#bnt-search').bind('click', function(){
					util.post(that.config.urlUserSearch, that.getSearchForm(), function(result) {
						that.renderUsers(result.entities);
					});
				});
				
				
				//load data of departments
				util.get(that.config.urlDepartmentLoad, function(result) {
					that.renderDepartments(result.entities);
					
					//load total users
					util.post(that.config.urlUserSearch, {}, function(result) {
						that.renderUsers(result.entities);
					});
				});
			},
			
			/* build request form */
			getSearchForm : function() {
				var data = { rules : [] };
				var accountE = $('#account');
				var nameE = $('#name');
				var ageMinE = $('#ageMin');
				var ageMaxE = $('#ageMax');
				var bornMinE = $('#bornMin');
				var bornMaxE = $('#bornMax');
				var departmentsE = $('#departments');
				
				if(accountE && accountE.val()) {
					data.rules.push( { field : 'account', oper : '=', value : accountE.val() } );
				}
				if(nameE && nameE.val()) {
					data.rules.push( { field : 'name', oper : 'like', value : nameE.val() } );
				}
				if(ageMinE && ageMinE.val()) {
					data.rules.push( { field : 'age', oper : '>=', value : ageMinE.val() } );
				}
				if(ageMaxE && ageMaxE.val()) {
					data.rules.push( { field : 'age', oper : '<=', value : ageMaxE.val() } );
				}
				if(bornMinE && bornMinE.val()) {
					data.rules.push( { field : 'bornOn', oper : '>=', value : bornMinE.val() } );
				}
				if(bornMaxE && bornMaxE.val()) {
					data.rules.push( { field : 'bornOn', oper : '<=', value : bornMaxE.val() } );
				}
				if(departmentsE && departmentsE.val() && departmentsE.val().length > 0) {
					data.rules.push( { field : 'departmentId', oper : 'in', value : departmentsE.val() } );
				}
				
				return data;
			},
			
			renderDepartments : function(departments) {
				var depSelect = $('#departments');
				for(var dp in departments) {
					var opt = '<option value="'+departments[dp].id+'">'+departments[dp].name+'</option>';
					depSelect.append(opt);
					this.depMap[departments[dp].id] = departments[dp];
				}

				$("#departments").multiselect({
					noneSelectedText: "===请选择===",
			        checkAllText: "全选",
			        uncheckAllText: '全不选'
				});
			},
			
			renderUsers : function(users) {
				$("#tbl-result .col").remove();//clear user list
				if(users && users.length > 0) {
					var table = $('#tbl-result');
					var sin = true;
					for(var i in users) {
						var tr = '<tr class="col '+(sin ? '' : 'alt')+'"><td>'+users[i].account+'</td><td>'+users[i].name+'</td>'+
									'<td>'+users[i].age+'</td><td>'+users[i].bornOn+'</td>'+
									'<td>'+this.depMap[users[i].departmentId].name+'</td></tr>';
						table.append(tr);
						sin = sin? false : true;
					}
				}
			}
	};
	
	global.app = app;
})(this);