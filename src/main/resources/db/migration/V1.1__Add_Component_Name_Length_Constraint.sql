ALTER TABLE components ADD CONSTRAINT not_empty_component_name CHECK(length(name) > 0);