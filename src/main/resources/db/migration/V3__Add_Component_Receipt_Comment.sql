ALTER TABLE component_receipts ADD COLUMN comment text;
ALTER TABLE component_receipts ADD CONSTRAINT not_empty_component_receipt_comment CHECK (length(comment) > 0);