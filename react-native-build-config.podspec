require 'json'


Pod::Spec.new do |s|
  package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

  s.name           = package['name']
  s.version        = package['version']
  s.summary        = package['description']
  s.homepage       = package['homepage']
  s.license        = package['license']
  s.author         = package['author']
  s.source         = { :git => s.homepage, :tag => 'v#{s.version}' }

  s.requires_arc   = true
  s.ios.deployment_target = '8.0'
  s.osx.deployment_target = '10.9'

  s.preserve_paths = 'README.md', 'package.json', 'lib/index.js'
  s.source_files   = 'ios/*.{h,m}'

  s.dependency 'React'
end
