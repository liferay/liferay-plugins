module Custom
	def Custom.navigation
		"""
			<div class='separator'></div>

			<div>
				Navigation:

				#{LiferayPortlet.renderLink("Home", "/WEB-INF/ruby/view.rb")} -
				#{LiferayPortlet.renderLink("User Info", "/WEB-INF/ruby/info.rb", {'type' => 'user'})} -
				#{LiferayPortlet.renderLink("Portlet Info", "/WEB-INF/ruby/info.rb", {'type' => 'portlet'})} -
				#{LiferayPortlet.renderLink("Invoke Java", "/WEB-INF/ruby/java.rb")}
			</div>
		"""
	end

	def Custom.sayHello
		"""
			<div>
				<b>Hello Ruby Portlet users!</b>
			</div>
		"""
	end

	def Custom.showNumber(renderRequest)
		preferences = renderRequest.getPreferences();
		num = preferences.getValue("number", "0")

		"""
			<div>
				Number: #{num} (#{LiferayPortlet.actionLink("Increment")})
			</div>
		"""
	end
end