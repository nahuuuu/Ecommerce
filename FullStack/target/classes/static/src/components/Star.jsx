
import Rating from '@mui/material/Rating';
import Stack from '@mui/material/Stack';

export default function Star({value}    ) {
  return (
    <Stack spacing={1}>
      <Rating name="half-rating-read" value={value}  defaultValue={2.5} precision={0.5} readOnly />
    </Stack>
  );
}