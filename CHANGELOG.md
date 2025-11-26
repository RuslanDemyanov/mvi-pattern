<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# mvi-pattern Changelog

## [Unreleased]

### Changed
- Migrate to IntelliJ Platform Gradle Plugin v2.x
- Update to Gradle 9.2.1
- Update to Kotlin 2.1.0
- Support latest Android Studio (2024.2 Koala and newer)
- Update GitHub Actions workflows to latest versions
- Replace detekt/ktlint with Qodana for code quality checks

### Removed
- Removed detekt and ktlint configurations (replaced by Qodana)

## [0.0.9] - 2024-03-04
### Added
- Support Android Studio Iguana.

### Changed
- Update Fragment generation.
- Update Screen generation.

## [0.0.8] - 2023-07-26
### Added
- Support Android Studio Giraffe.

### Changed
- Update Fragment generation.

## [0.0.7] - 2022-09-20
### Changed
- The plugin now only supports Android Studio Dolphin version.

## [0.0.6] - 2022-08-18
### Added
- Add Koin annotations for side effects and view model.

### Changed
- Update Composable screen generation.

### Fixed
- Fix the issue when files are generated in the wrong directory.
- Correct generation of mutation tests.

## [0.0.5] - 2022-02-07
### Changed
- Add Composable screen generation and update the generated Fragment code.
- Add router side effect generation (specify container name in the wizard).
- Add generation of initial MVI unit tests.
- Remove the ability to generate XML layout.

## [0.0.4] - 2021-09-28
### Changed
- Change the plugin icon.
- Fix the generated code of fragment layout.
- Update dependencies.

## [0.0.3] - 2021-08-02
### Changed
- Update dependencies.

## [0.0.2] - 2021-06-12
### Changed
- Refactoring the generated code of Fragment.

## [0.0.1] - 2021-05-08
### Added
- The first plugin release.
