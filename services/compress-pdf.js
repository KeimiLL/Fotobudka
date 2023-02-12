const execa = require('execa')

module.exports = (input, output, opts = {}) => {
  const cmd = process.env.GHOSTSCRIPT_COMMAND || opts.command || 'gs'

  const {
    compatibilityLevel = 1.5,
    compressFonts = true,
    embedAllFonts = true,
    subsetFonts = true,
    dpi = 300,
    quiet = true,
    preset = 'screen',
    colorConversionStrategy = 'RGB',
  } = opts

  const args = [
    '-sDEVICE=pdfwrite',
    `-dPDFSETTINGS=/${preset}`,
    '-dNOPAUSE',
    quiet ? '-dQUIET' : '',
    '-dBATCH',
    `-dCompatibilityLevel=${compatibilityLevel}`,
    // font settings
    `-dSubsetFonts=${subsetFonts}`,
    `-dCompressFonts=${compressFonts}`,
    `-dEmbedAllFonts=${embedAllFonts}`,
    // color format
    '-sProcessColorModel=DeviceRGB',
    `-sColorConversionStrategy=${colorConversionStrategy}`,
    `-sColorConversionStrategyForImages=${colorConversionStrategy}`,
    '-dConvertCMYKImagesToRGB=true',
    // image resampling
    '-dDetectDuplicateImages=true',
    '-dColorImageDownsampleType=/Bicubic',
    `-dColorImageResolution=${dpi}`,
    '-dGrayImageDownsampleType=/Bicubic',
    `-dGrayImageResolution=${dpi}`,
    '-dMonoImageDownsampleType=/Bicubic',
    `-dMonoImageResolution=${dpi}`,
    '-dDownsampleColorImages=true',
    // other overrides
    '-dDoThumbnails=false',
    '-dCreateJobTicket=false',
    '-dPreserveEPSInfo=false',
    '-dPreserveOPIComments=false',
    '-dPreserveOverprintSettings=false',
    '-dUCRandBGInfo=/Remove',
    '-dAutoRotatePages=/None',
    `-sOutputFile="${output}"`,
    `"${input}"`
  ]

  if (!quiet)
    console.log(`${cmd} ${args.join(' ')}`)

  return execa(cmd, args, { stdio: 'inherit', shell: true })
}
