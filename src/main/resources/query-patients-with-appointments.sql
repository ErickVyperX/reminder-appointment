SELECT DISTINCT
    appo.patient_key AS ID,
    p.surname1 || ' ' || p.surname2 || ' ' || p.name AS FullName,
    p.family_telephone AS Cellphone,
    appo.date_time AS AppointmentDate,
    appo.insert_date AS RegisterDate,
    l.default_text AS AttentionArea,
    appo.reminder_sent AS ReminderSent
FROM
    health_kernel.appointment appo
        INNER JOIN ehcos.professional pro ON pro.professional_key = appo.professional_schedule_key
        INNER JOIN ehcos.eh_user us USING(user_key)
        LEFT JOIN health_kernel.agenda_schedule agsch ON appo.agenda_schedule_key = agsch.agenda_schedule_key
        LEFT JOIN health_kernel.agenda agn ON agsch.agenda_key = agn.agenda_key
        LEFT JOIN ehcos.label l ON agn.long_desc_label_key = l.label_code
        INNER JOIN health_kernel.section_service_center ssc ON appo.section_service_center_key = ssc.section_service_center_key
        INNER JOIN health_kernel.rendering rend ON appo.rendering_key = rend.rendering_key
        INNER JOIN health_kernel.basic_rendering_master brm ON rend.rendering_master_key = brm.rendering_master_key
        INNER JOIN ehcos.label ll ON brm.long_desc_label = ll.label_code
        INNER JOIN health_kernel.patient p ON appo.patient_key = p.patient_key
WHERE
    (DATE(appo.date_time) = CURRENT_DATE + INTERVAL '1 DAY' OR DATE(appo.date_time) = CURRENT_DATE + INTERVAL 'TO_RANGE_VALUE DAY')
  AND annulment_reason_type IS NULL
  AND appo.deleted = FALSE
  AND pro.deleted = FALSE
  AND us.deleted = FALSE
  AND us.inactive_date IS NULL
  AND l.deleted = FALSE
  AND agsch.deleted = FALSE
  AND agn.deleted = FALSE
  AND ssc.deleted = FALSE
  AND ssc.area_id = 4
  AND ssc.section_service_center_key IN (11000175, 11000180, 11000288, 11000280, 11000208, 11000089, 11000090, 11000190, 11000088, 11000207, 11000091, 11000226, 11000179, 11000271, 11000178, 11000230, 11000206, 11000176, 11000181, 11000191, 11000231, 11000189, 11000203, 11000111, 11000199, 11000278, 11000177)
  AND p.family_telephone IS NOT NULL AND p.family_telephone <> '' AND length(p.family_telephone) = 9
  AND appo.reminder_sent IS NOT TRUE
ORDER BY appo.insert_date;