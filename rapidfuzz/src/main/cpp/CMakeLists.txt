cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_CXX_STANDARD 17)

set(C_FLAGS "-Werror=format -fdata-sections -ffunction-sections -fno-rtti -fno-threadsafe-statics")
set(LINKER_FLAGS "-Wl,--hash-style=both")

if (NOT CMAKE_BUILD_TYPE STREQUAL "Debug")
    message("Builing Release...")

    set(C_FLAGS "${C_FLAGS} -O2 -fvisibility=hidden -fvisibility-inlines-hidden")
    set(LINKER_FLAGS "${LINKER_FLAGS} -Wl,-exclude-libs,ALL -Wl,--gc-sections")
else()
    message("Builing Debug...")

    add_definitions(-DDEBUG)
endif ()

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${C_FLAGS}")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${C_FLAGS}")

set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} ${LINKER_FLAGS}")
set(CMAKE_MODULE_LINKER_FLAGS "${CMAKE_MODULE_LINKER_FLAGS} ${LINKER_FLAGS}")

find_library(log-lib log)

# Compile rapidfuzz-cpp
set(HEADER_FILES
	rapidfuzz-cpp/rapidfuzz/fuzz.hpp
	rapidfuzz-cpp/rapidfuzz/fuzz_impl.hpp
	rapidfuzz-cpp/rapidfuzz/string_metric.hpp
	rapidfuzz-cpp/rapidfuzz/utils.hpp
	rapidfuzz-cpp/rapidfuzz/utils_impl.hpp
	rapidfuzz-cpp/rapidfuzz/details/SplittedSentenceView.hpp
	rapidfuzz-cpp/rapidfuzz/details/type_traits.hpp
	rapidfuzz-cpp/rapidfuzz/details/types.hpp
	rapidfuzz-cpp/rapidfuzz/details/unicode.hpp
	rapidfuzz-cpp/rapidfuzz/details/matching_blocks.hpp
	rapidfuzz-cpp/rapidfuzz/details/string_view.hpp
	rapidfuzz-cpp/rapidfuzz/details/string_metrics/levenshtein_impl.hpp
	rapidfuzz-cpp/rapidfuzz/details/string_metrics/weighted_levenshtein_impl.hpp
	rapidfuzz-cpp/rapidfuzz/details/string_metrics/generic_levenshtein_impl.hpp
	rapidfuzz-cpp/rapidfuzz/details/common.hpp
	rapidfuzz-cpp/rapidfuzz/details/common_impl.hpp
)

# Compile rapidfuzz
add_library(rapidfuzz SHARED io_github_muntashirakon_rapidfuzz_RapidFuzz.cpp ${HEADER_FILES})

target_link_libraries(rapidfuzz ${log-lib})
target_include_directories(rapidfuzz PUBLIC
 $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/>)

if (NOT CMAKE_BUILD_TYPE STREQUAL "Debug")
    add_custom_command(TARGET rapidfuzz POST_BUILD
            COMMAND ${CMAKE_STRIP} --remove-section=.comment "${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/librapidfuzz.so")
endif ()
